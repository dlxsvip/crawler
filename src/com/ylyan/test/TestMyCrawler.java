package com.ylyan.test;

import com.ylyan.crawler.MyCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class TestMyCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder("./storage/0.0.0.0");
		config.setConnectionTimeout(3000);
		config.setMaxDepthOfCrawling(5);
		config.setMaxPagesToFetch(3000);
		config.setPolitenessDelay(1000);
		config.setResumableCrawling(false);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);

		try {
			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);
			controller.addSeed("http://www.baidu.com");
			controller.start(MyCrawler.class, 5);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
