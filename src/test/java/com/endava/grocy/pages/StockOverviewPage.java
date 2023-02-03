package com.endava.grocy.pages;

import Cucumber.ScenarioContext;
import Enums.DataKeys;
import com.endava.grocy.client.BaseClient;
import com.endava.grocy.model.Stock;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**NOTE: these tests are deprecated,
 *  please refer to the Cucumber step implementation*/
public class StockOverviewPage extends BaseClient {
    @Test
    public void shouldNavigateToStockJournal() {

        String expectedTitle = "Stock journal | grocy";

        Response getStockJournalResponse = getBasicRestConfig("")
                .when()
                .get("/stockjournal");
        getStockJournalResponse
                .then()
                .statusCode(HttpStatus.SC_OK);

        Document doc = Jsoup.parse(getStockJournalResponse.asString());
        Element titleElement = doc.select("title").first();

        Assertions.assertEquals(expectedTitle.toLowerCase(), titleElement.text().toLowerCase());
    }

    @Test
    public void shouldNavigateToStockEntries() {
        String expectedTitle = "Stock entries | grocy";

        Response getStockJournalResponse = getBasicRestConfig("")
                .when()
                .get("/stockentries");
        getStockJournalResponse
                .then()
                .statusCode(HttpStatus.SC_OK);

        Document doc = Jsoup.parse(getStockJournalResponse.asString());
        Element titleElement = doc.select("title").first();
        Assertions.assertEquals(expectedTitle.toLowerCase(), titleElement.text().toLowerCase());
    }

    @Test
    public void shouldNavigateToLocationContentSheet() {
        String expectedTitle = "Location Content Sheet | grocy";
        Response getStockJournalResponse = getBasicRestConfig("")
                .when()
                .get("/locationcontentsheet");
        getStockJournalResponse
                .then()
                .statusCode(HttpStatus.SC_OK);

        Document doc = Jsoup.parse(getStockJournalResponse.asString());
        Element titleElement = doc.select("title").first();

        Assertions.assertNotEquals(expectedTitle.toLowerCase(), titleElement.text().toLowerCase());
    }

    private Stock getStockDetails() {
        ValidatableResponse stockDataResponse = getBasicRestConfig("api")
                .get("/stock")
                .then()
                .statusCode(HttpStatus.SC_OK);
        return stockDataResponse.extract().body().as(Stock.class);
    }

    @Test
    public void shouldDisplayTotalAmount() {
        //#info-current-stock
        /*retrieve the stock amount and value from
         * http://3.65.154.68:8089/api/stock
         * Save the response and map the n products from the stock
         * */

        //System.out.println(getStockDetails().getAmount());

    }

}
