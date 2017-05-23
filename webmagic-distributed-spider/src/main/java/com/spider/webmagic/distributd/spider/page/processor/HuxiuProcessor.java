package com.spider.webmagic.distributd.spider.page.processor;

import java.util.List;

import com.spider.webmagic.distributd.spider.page.domain.BaseItem;
import com.spider.webmagic.distributd.spider.page.domain.TestItem;
import com.spider.webmagic.distributd.spider.page.pipline.WebmagicPipLine;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class HuxiuProcessor
  implements PageProcessor
{
  public void process(Page page)
  {
    try
    {

        List requests = page.getHtml().links().regex(".*article.*").all();
        page.addTargetRequests(requests);

        String title = page.getHtml().xpath("//div[@class='article-wrap']//h1/text()").get();

        String author = page.getHtml().xpath("//div[@class='article-wrap']//div[@class='article-author']//span[@class='author-name']//a/text()")
          .get();

        BaseItem bitem = new BaseItem();
        bitem.setUnname("huxiu");

        TestItem titem = new TestItem();
        titem.setTitle(title);
        titem.setAuthor(author);
        bitem.getUndata().add(titem);

        page.putField("bitem", bitem);

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Site getSite()
  {
    return Site.me().setDomain("www.huxiu.com");
  }

  public static void main(String[] args) {
    Spider.create(new HuxiuProcessor()).addUrl(new String[] { "http://www.huxiu.com/" })
      .addPipeline(new WebmagicPipLine()).run();
  }
}