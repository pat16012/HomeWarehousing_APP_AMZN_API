package homewarehouse.project.homewarehouse_amzn_api;

import com.indix.client.IndixApiClient;
import com.indix.client.impl.IndixApiClientFactory;
import com.indix.exception.IndixApiException;
import com.indix.models.productDetailsResult.SummaryProductDetailsResult;
import com.indix.models.productDetailsResult.UniversalProductDetailsResult;
import com.indix.models.searchResult.CatalogPremiumSearchResult;
import com.indix.models.searchResult.CatalogStandardSearchResult;
import com.indix.models.searchResult.OffersSearchResult;
import com.indix.models.searchResult.SummarySearchResult;
import com.indix.models.searchResult.UniversalSearchResult;
import com.indix.query.ProductDetailsQuery;
import com.indix.query.Query;
import com.indix.query.QueryFactory;
import com.indix.httpClient.HttpClient;
import com.indix.httpClient.impl.HttpClientFactory;
import com.indix.tools.SSLTrustCA;

import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;


public class IndixAPI implements SearchAPI{
    String appKey = "QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";


    //HttpClient client = HttpClientFactory.newHttpClient(HttpClients.custom()
      //      .setSslcontext(SSLTrustCA.trustLetsEncryptRootCA())
        //    .build());
    IndixApiClient indixApiClient = IndixApiClientFactory
            .newIndixApiClient(appKey);


    @Override
    public SummarySearchResult getProductsSummary(Query query) throws IndixApiException, IOException, URISyntaxException {

        try {
            String mpid = "";
            ProductDetailsQuery productDetailsQuery =
                    QueryFactory.newProductDetailsQuery()
                            .withCountryCode("US")
                            .withMpid(mpid);

            UniversalProductDetailsResult pr = indixApiClient
                    .getProductDetailsUniversal(productDetailsQuery);
            System.out.println(pr.getProduct().getUpcs());

        } finally {
            indixApiClient.close();
        }

        return null;
    }

    @Override
    public OffersSearchResult getProductsOffersStandard(Query query) throws IndixApiException, URISyntaxException, IOException {
        return null;
    }

    @Override
    public OffersSearchResult getProductsOffersPremium(Query query) throws IndixApiException, IOException, URISyntaxException {
        return null;
    }

    @Override
    public CatalogStandardSearchResult getProductsCatalogStandard(Query query) throws IndixApiException, IOException, URISyntaxException {
        return null;
    }

    @Override
    public CatalogPremiumSearchResult getProductsCatalogPremium(Query query) throws IndixApiException, IOException, URISyntaxException {
        return null;
    }

    @Override
    public UniversalSearchResult getProductsUniversal(Query query) throws IndixApiException, IOException, URISyntaxException {
        return null;
    }
}
