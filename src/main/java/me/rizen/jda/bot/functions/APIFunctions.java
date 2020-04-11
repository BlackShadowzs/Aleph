/*
 * Aleph, Advanced Discord Bot
 *      Copyright (C) 2020 "R1zeN" Jonas Schiøtt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.rizen.jda.bot.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.duncte123.botcommons.web.ContentType;
import me.duncte123.botcommons.web.WebParserUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import okhttp3.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static me.rizen.jda.bot.functions.MessageFunctions.*;

public class APIFunctions {
    private static final String HASTE_SERVER = "https://hasteb.in/";
    static Language getGuildLanguage(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId);
    }
    public static void getLyrics(TextChannel channel, Member member, String text) {
        WebUtils.ins.getJSONObject("https://api.genius.com/search?q=" + text + "&access_token=" + Config.getInstance().getString("geniusKey")).async((json) -> {
            String path = json.findValue("path").asText();
            String fullTitle = json.findValue("full_title").asText();
            WebUtils.ins.scrapeWebPage("https://genius.com" + path).async((page) -> {
                String lyrics = page.getElementsByClass("lyrics").eachText().toString();
                lyrics = lyrics.length() >= 1950 ? lyrics.substring(1, 1950) : lyrics;
                sendEmbed(channel, createEmbed(member)
                        .setTitle(fullTitle)
                        .setColor(Color.PINK)
                        .addField(getGuildLanguage(channel.getGuild().getId()).LYRICS_FIELD_NAME(), getGuildLanguage(channel.getGuild().getId()).LYRICS_FIELD_VALUE(), false)
                        .setDescription(lyrics));

            });
        });
    }

    public static void invertImage(TextChannel channel, User target) throws IOException {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        String effectiveAvatarUrl = target.getEffectiveAvatarUrl();
        OkHttpClient client = new OkHttpClient();
        RequestBody req = new FormBody.Builder()
                .add("image", effectiveAvatarUrl)
                .build();

        Request request = new Request.Builder()
                .url("https://apis.duncte123.me/filters/invert")
                .post(req)
                .build();

        Call call = client.newCall(request);
        Response res = call.execute();

        if (res.code() == 429) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
            return;
        }

        assert res.body() != null;
        channel.sendMessage(language.INVERT_MESSAGE().replace("REPLACE", target.getAsTag()))
                .addFile(Objects.requireNonNull(res.body()).bytes(), "Aleph.png").queue();
    }

    public static void createDrakeMeme(TextChannel channel, String topAndBottomLine) throws IOException {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        String top = topAndBottomLine.split(":")[0];
        String bottom = topAndBottomLine.split(":")[1];
        OkHttpClient client = new OkHttpClient();
        RequestBody req = new FormBody.Builder()
                .add("top", top)
                .add("bottom", bottom)
                .build();

        Request request = new Request.Builder()
                .url("https://apis.duncte123.me/memes/drakememe")
                .post(req)
                .build();

        Call call = client.newCall(request);
        Response res = call.execute();

        if (res.code() == 429) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
            return;
        }

        assert res.body() != null;
        channel.sendMessage(language.DRAKE_MESSAGE())
                .addFile(Objects.requireNonNull(res.body()).bytes(), "Aleph.png").queue();
    }

    public static void sendRandomFact(TextChannel channel) {
        WebUtils.ins.getJSONObject("https://sparse.pw/api/fact?key=" + Config.getInstance().getString("sparseKey")).async(
                (json) -> {
                    String data = json.get("data").asText();
                    sendMessage(channel, data);
                }
        );
    }

    public static void sendWeatherInformation(TextChannel channel, Member member, String location) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
            WebUtils.ins.getJSONArray("https://sparse.pw/api/checkweather?key=" + Config.getInstance().getString("sparseKey") + "&loc=" + location).async(
                    (json) -> {

                        String locationName = json.findValue("name").asText();
                        String weatherKind = json.findValue("skytext").asText();
                        String coldest = json.findValue("low").asText();
                        String warmest = json.findValue("high").asText();
                        String temperature = json.findValue("temperature").asText();

                        System.out.print(json.toPrettyString());


                        sendEmbed(channel, createEmbed(member)
                                .setAuthor(locationName)
                                .addField(language.WEATHER_LOW(), coldest + "°F", true)
                                .addField(language.WEATHER_HIGH(), warmest + "°F", true)
                                .addField(language.WEATHER_TEMPERATURE(), temperature + "°F", true)
                                .setTitle(weatherKind));

                    }
            );
    }

    public static void htmlToPdf(TextChannel channel, String html) throws IOException {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        String apiKey = Config.getInstance().getString("html2pdfKey");
        OkHttpClient client = new OkHttpClient();
        RequestBody req = new FormBody.Builder()
                .add("html", html)
                .add("apiKey", apiKey)
                .build();

        Request request = new Request.Builder()
                .url("https://api.html2pdf.app/v1/generate")
                .post(req)
                .build();

        Call call = client.newCall(request);
        Response res = call.execute();

        if (res.code() == 429) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
            return;
        }
        assert res.body() != null;
        channel.sendFile(res.body().bytes(), "alephHtmlToPdf.pdf", new net.dv8tion.jda.api.utils.AttachmentOption[]{}).queue();
    }

    public static void randomQuote(TextChannel channel) throws IOException {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        Request req = WebUtils.defaultRequest().addHeader("x-rapidapi-host", "andruxnet-random-famous-quotes.p.rapidapi.com")
                .addHeader("x-rapidapi-key", Config.getInstance().getString("rakutenKey"))
                .get().url("https://andruxnet-random-famous-quotes.p.rapidapi.com/?cat=famous&count=1").build();
        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(req).execute();
        if (response.code() == 429) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
            return;
        }
        assert response.body() != null;
        String resStr = response.body().toString();
        JSONObject json = new JSONObject(resStr);
        String author = json.getJSONArray(String.valueOf(json)).getString(0);
        System.out.print(author);
        //  String quote = json.getString("quote");

      /*  sendEmbed(channel, createEmbed(member)
                .setTitle(author)
                .setDescription("```css\n" + quote + "```"));*/
    }

    public static void createFreeRealEstateMeme(TextChannel channel, String text) throws IOException {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        OkHttpClient client = new OkHttpClient();
        RequestBody req = new FormBody.Builder()
                .add("text", text)
                .build();

        Request request = new Request.Builder()
                .url("https://apis.duncte123.me/memes/itsfreerealestate")
                .post(req)
                .build();

        Call call = client.newCall(request);
        Response res = call.execute();

        if (res.code() == 429) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
            return;
        }

        assert res.body() != null;
        channel.sendMessage(language.FREE_REAL_MESSAGE())
                .addFile(Objects.requireNonNull(res.body()).bytes(), "Aleph.png", new net.dv8tion.jda.api.utils.AttachmentOption[]{}).queue();
    }

    public static void getRandomCocktail(TextChannel channel, Member member) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        WebUtils.ins.getJSONObject("https://www.thecocktaildb.com/api/json/v1/1/random.php").async((json) -> {
            final String strDrink = json.findValue("strDrink").asText();
            final String alcoholic = json.findValue("strAlcoholic").asText();
            final String dateModified = json.findValue("dateModified").asText();
            final String strInstructions = json.findValue("strInstructions").asText();
            final String servingGlass = json.findValue("strGlass").asText();
            final String pictureOfDrink = json.findValue("strDrinkThumb").asText();
            HashMap<String, String> ingredientsWMeasures = new HashMap<>();

            String isAlchoholic = alcoholic.equals("Alcoholic") ? "Yes" : "No";

            final EmbedBuilder builder = createEmbed(member)
                    .setTitle(strDrink)
                    .setThumbnail(pictureOfDrink)
                    .setDescription(language.COCKTAIL_INIT_DESCRIPTION())
                    .addField(language.COCKTAIL_ALCOHOLIC(), isAlchoholic, true)
                    .addField(language.COCKTAIL_LAST_MODIFIED(), servingGlass, true)
                    .setFooter(language.COCKTAIL_LAST_MODIFIED() + dateModified);

            for (int i = 1; i <= 15 ; i++) {
                int finalI = i;
                json.forEach((x) ->  {
                    JsonNode ing = json.findValue("strIngredient"+ finalI);
                    JsonNode measure = json.findValue("strMeasure"+ finalI);
                    if (ing != null && measure != null && !ing.asText().equals("null") && !measure.asText().equals("null")) {
                        ingredientsWMeasures.put(ing.asText(), measure.asText());
                    }
                });
            }


            ingredientsWMeasures.forEach((in, mes) -> builder.appendDescription(in+" - "+mes+"\n"));
            builder.appendDescription("\n"+strInstructions);
            builder.appendDescription("```");
            sendEmbed(channel, builder);
        });
    }

    public static void searchCocktail(TextChannel channel, Member member, String cocktailName) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        WebUtils.ins.getJSONObject("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+cocktailName).async((json) -> {
            final String strDrink = json.findValue("strDrink").asText();
            final String alcoholic = json.findValue("strAlcoholic").asText();
            final String dateModified = json.findValue("dateModified").asText();
            final String strInstructions = json.findValue("strInstructions").asText();
            final String servingGlass = json.findValue("strGlass").asText();
            final String pictureOfDrink = json.findValue("strDrinkThumb").asText();
            HashMap<String, String> ingredientsWMeasures = new HashMap<>();

            String isAlchoholic = "Alcoholic".equalsIgnoreCase(alcoholic) ? "Yes" : "No";

            final EmbedBuilder builder = createEmbed(member)
                    .setTitle(strDrink)
                    .setThumbnail(pictureOfDrink)
                    .setDescription(language.COCKTAIL_INIT_DESCRIPTION())
                    .addField(language.COCKTAIL_ALCOHOLIC(), isAlchoholic, true)
                    .addField(language.COCKTAIL_LAST_MODIFIED(), servingGlass, true)
                    .setFooter(language.COCKTAIL_LAST_MODIFIED() + dateModified);

            for (int i = 1; i <= 15 ; i++) {
                int finalI = i;
                json.forEach((x) ->  {
                    JsonNode ing = json.findValue("strIngredient"+ finalI);
                    JsonNode measure = json.findValue("strMeasure"+ finalI);
                    if (ing != null && measure != null && !ing.asText().equals("null") && !measure.asText().equals("null")) {
                        ingredientsWMeasures.put(ing.asText(), measure.asText());
                    }
                });
            }


            ingredientsWMeasures.forEach((in, mes) -> builder.appendDescription(in+" - "+mes+"\n"));
            builder.appendDescription("\n"+strInstructions);
            builder.appendDescription("```");
            sendEmbed(channel, builder);
        });
    }
    public static void yodanize(TextChannel channel, String text) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        try {
        RequestBody req = new FormBody.Builder()
                .add("text", text)
                .build();

        Request request = WebUtils.defaultRequest()
                .post(req)
                .url("https://api.funtranslations.com/translate/yoda.json")
                .build();

        WebUtils.ins.prepareRaw(request, (r) -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async((json) -> {
            final String translated = json.findValue("translated").asText();

            sendMessage(channel, language.YODA_WOULD_SAY().replace("REPLACE", translated));
        });
    } catch (NullPointerException e) {
        sendMessage(channel, language.ERROR_RATE_LIMIT());
    }
    }

    public static void mandalorianize(TextChannel channel, String text) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        try {
        RequestBody req = new FormBody.Builder()
                .add("text", text)
                .build();

        Request request = WebUtils.defaultRequest()
                .post(req)
                .url("https://api.funtranslations.com/translate/mandalorian.json")
                .build();

        WebUtils.ins.prepareRaw(request, (r) -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async((json) -> {
            final String translated = json.findValue("translated").asText();

            sendMessage(channel, language.IN_MANDALORIAN_THAT_IS().replace("REPLACE", translated));
        });
        } catch (NullPointerException e) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
        }
    }
    public static void sithify(TextChannel channel, String text) {
        final Language language = getGuildLanguage(channel.getGuild().getId());
        try {
            RequestBody req = new FormBody.Builder()
                    .add("text", text)
                    .build();

            Request request = WebUtils.defaultRequest()
                    .post(req)
                    .url("https://api.funtranslations.com/translate/sith.json")
                    .build();

            WebUtils.ins.prepareRaw(request, (r) -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async((json) -> {
                final String translated = json.findValue("translated").asText();

                sendMessage(channel, language.IN_SITH_THAT_IS().replace("REPLACE", translated));
            });
        } catch (NullPointerException e) {
            sendMessage(channel, language.ERROR_RATE_LIMIT());
        }
    }

    public static void sendJoke(TextChannel channel) {
        List<String> jokeCat = new ArrayList<>();
        jokeCat.add("https://sv443.net/jokeapi/v2/joke/Any?blacklistFlags=nsfw,racist,sexist&type=single");
        jokeCat.add("https://sv443.net/jokeapi/v2/joke/Any?blacklistFlags=nsfw,racist,sexist&type=twopart");
        int[] jokeChoser = {0, 1};
        int rnd = new Random().nextInt(jokeChoser.length);

        final String apiUrl = jokeCat.get(jokeChoser[rnd]);

        WebUtils.ins.getJSONObject(apiUrl).async((json) -> {
            if (apiUrl.contains("single")) {
                final String joke = json.findValue("joke").asText();
                sendMessage(channel, joke);
                return;
            }
            final String setup = json.findValue("setup").asText();
            final String delivery = json.findValue("delivery").asText();
            channel.sendMessage(setup).queue((chan) -> channel.sendMessage(delivery).queueAfter(4, TimeUnit.SECONDS));
        });
    }

    public static void checkWebsiteState (TextChannel channel, Member member, String website) {
        WebUtils.ins.scrapeWebPage("https://check-host.net/check-http?host="+website).async((json) -> {
            System.out.print(json.toString());
            final String firstTime = json.getElementById("result_fi1.node.check-host.net").text();
            final String secondTime = json.getElementsByClass("time").get(1).data();
            final String thirdTime = json.getElementsByClass("time").get(2).data();
            final String firstLocation = json.getElementsByClass("location").get(0).data();
            final String secondLocation = json.getElementsByClass("location").get(1).data();
            final String thirdLocation = json.getElementsByClass("location").get(2).data();
            final String firstCode = json.getElementsByClass("code").get(0).data();
            final String secondCode = json.getElementsByClass("code").get(1).data();
            final String thirdCode = json.getElementsByClass("code").get(2).data();
            final String result = json.getElementsByClass("result").get(0).data();

            System.out.print(firstTime+"\n\n\n"+firstLocation+"\n\n\n"+firstCode);

            sendEmbed(channel, createEmbed(member)
                    .setTitle(website)
                    .addField(firstLocation + " " + firstCode, firstTime, true)
                    .addField(secondLocation + " " + secondCode, secondTime, true)
                    .addField(thirdLocation + " " + thirdCode, thirdTime, true)
                    .setFooter(result)

            );
        });
    }

    public static void postToHastebin (String body, Consumer<String> callback) {
        Request req = WebUtils.defaultRequest()
                .post(RequestBody.create(null, body.getBytes()))
                .addHeader("Content-Type", ContentType.TEXT_HTML.getType())
                .url(HASTE_SERVER + "documents")
                .build();

        WebUtils.ins.prepareRaw(req, r -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async((json) -> {
            String key = json.get("key").asText();

            callback.accept(HASTE_SERVER + key);
        },
        (e) -> callback.accept("Error: " + e.getMessage()));
    }
}


