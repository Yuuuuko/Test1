package com.jz;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;


public class luceneIndex {
    @Test
    public void createIndex() throws IOException {
        //创建directory对象，指向索引存放目录
        Directory directory= FSDirectory.open(new File("E:\\Test\\LuceneIndex").toPath());
        //创建indexWriter对象
        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig());
        //获取要检索的文件
        File file = new File("E:\\黑马教学\\就业班\\SSM\\lucene\\资料\\searchsource");
        File[] files = file.listFiles();

        for (File f : files) {
            String fileName = f.getName();
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            long fileSize = FileUtils.sizeOf(f);
            String filePath = f.getPath();
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            Field fieldPath=new TextField("path",filePath, Field.Store.YES);
            Field fieldSize=new TextField("size",fileSize+"", Field.Store.YES);
            Document document=new Document();
            document.add(fieldName);
            document.add(fieldContent);
            document.add(fieldPath);
            document.add(fieldSize);
            indexWriter.addDocument(document);
        }
        indexWriter.close();


    }

    @Test
    public void createIKindex() throws IOException {
        Directory directory=FSDirectory.open(new File("E:\\Test\\LuceneIndex").toPath());
        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));

        Field fieldname=new StringField("name","中文全文检索", Field.Store.YES);
        Field fieldContent=new TextField("content","eclipse下载版是一个开放源代码的(苟利国家生死以)、基于Java的可扩展开发平台。就eclipse下载版本身而言，它只是一个框架和一组服务，用于通过插件组件构建开发环境。幸运的是，Eclipse 附带了一个标准的插件集，包括 Java 开发工具（Java Development Tools，JDT）。eclipse下载还包括插件开发环境（Plug-in Development Environment，PDE），这个组件主要针对希望扩展eclipse的软件开发人员，因为它允许他们构建与eclipse环境无缝集成的工具。由于eclipse 中的每样东西都是插件，对于给eclipse提供插件，以及给用户提供一致和统一的集成开发环境而言，所有工具开发人员都具有同等的发挥场所，包括eclipse下载32位,eclipse下载64位本站提供eclipse中文版下载。", Field.Store.YES);
        Field fieldpath=new StoredField("path","E:\\Test\\LuceneIndex");
        Field fieldsize=new LongPoint("size",10000000l);
        Field fieldSize=new StoredField("size",10000000l);
        Document document=new Document();
        document.add(fieldname);
        document.add(fieldContent);
        document.add(fieldpath);
        document.add(fieldsize);
        document.add(fieldSize);

        indexWriter.addDocument(document);
        indexWriter.close();


    }



}
