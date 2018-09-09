package com.jz;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class LuceneSearch {
    @Test
    public void searchWord() throws IOException {
      /*  Directory directory= FSDirectory.open(new File("E:\\Test\\LuceneIndex").toPath());
        IndexReader indexReader= DirectoryReader.open(directory);

        IndexSearcher indexSearcher=new IndexSearcher(indexReader);

        Query query=new TermQuery(new Term("name","spring.txt"));

        TopDocs topDocs=indexSearcher.search(query,10);
        System.out.println("最大查询结果条数:"+topDocs.totalHits);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("name"));
            System.out.println(document.get("content"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println("------------------------------------------------------------");

        }
        indexReader.close();*/


            //指定索引库存放的路径
            //D:\temp\index
            Directory directory = FSDirectory.open(new File("E:\\Test\\LuceneIndex").toPath());
            //创建indexReader对象
            IndexReader indexReader = DirectoryReader.open(directory);
            //创建indexsearcher对象
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //创建查询
            Query query = new TermQuery(new Term("content", "下载"));
            //执行查询
            //第一个参数是查询对象，第二个参数是查询结果返回的最大值
            TopDocs topDocs = indexSearcher.search(query, 10);
            //查询结果的总条数
            System.out.println("查询结果的总条数："+ topDocs.totalHits);
            //遍历查询结果
            //topDocs.scoreDocs存储了document对象的id
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                //scoreDoc.doc属性就是document对象的id
                //根据document的id找到document对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document.get("name"));
                System.out.println(document.get("content"));
                System.out.println(document.get("path"));
                System.out.println(document.get("size"));
                System.out.println("-------------------------");
            }
            //关闭indexreader对象
            indexReader.close();


    }
}
