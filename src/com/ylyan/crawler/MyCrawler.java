package com.ylyan.crawler;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
	private static final Pattern FILTERS = Pattern
			.compile("(?i)^https.*|.*(\\.(css|js|txt|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|rmvb|smil|wmv|swf|wma|zip|rar|gz|7z|exe|msi|rpm))$");

	private static final Set<String> URLSET = new ConcurrentSkipListSet<String>();

	// visit则是爬取该URL所指向的页面的数据，其传入的参数即是对该web页面全部数据的封装对象Page。
	@Override
	public void visit(Page page) {

		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		List<WebURL> links = htmlParseData.getOutgoingUrls();
		Iterator<WebURL> i$ = links.iterator();
		while (i$.hasNext()) {
			WebURL link = (WebURL) i$.next();
			String url = link.getURL();
			if (validUrl(url))
				if ((link.getDepth() >= 0) && (URLSET.add(url)))
					System.out.println(url);
		}
	}

	private boolean validUrl(String url) {
		if ("".equals(url))
			return false;
		if (url.matches("(?i)https://.*"))
			return false;
		return url.getBytes().length < 255;
	}

	// shouldVisit是判断当前的URL是否已经应该被爬取（访问）；
	public boolean shouldVisit(WebURL url) {
		return !FILTERS.matcher(url.getURL()).matches();
	}
}
