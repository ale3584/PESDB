import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

import static java.lang.Integer.*;

class Main {
    private static final String BASE_URL = "http://pesdb.net/pes2020/";

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(BASE_URL + "?all=1").get();
        List<String> pagesUrl = getListOfPagesUrl(doc);
    }

    public static List<String> getListOfPagesUrl(Document doc) {
        List<String> listUrls = new ArrayList();
        Elements links = doc.select(".pages a");
        Element first = links.first();
        Element last = links.last();
        int firstIndex = valueOf(first.text());
        int lastIndex = valueOf(last.text());

        for (int i = firstIndex; i <= lastIndex; i++) {
             listUrls.add(getPageUrl(i));
        }
        return listUrls;
    }

    public static String getPageUrl(int pageNumber) {
        int index = (pageNumber <= 0) ? 1 : pageNumber;
        return (index != 1) ? BASE_URL + "?page=$" + pageNumber : BASE_URL;
    }
}
