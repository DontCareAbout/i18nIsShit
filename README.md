這是什麼東西？
==============

試圖用 Google Sheet（CSV）解決 i18n 需求的 tool :dancer:

管理 i18n 的語系檔是一件很惱人的事情，
我們認為用 Google Sheet 來管理、然後用程式自動產出檔案是比較合理的作法，
因此就惡搞出這個玩意出來。


為什麼要用 Google Sheet？
=========================

* 超強的多人協作功能。
	沒有 version control 的困擾、但是有 version control 的能力。
* 檔案不怕不見
* 不用錢

唯一的缺點就是這個 sheet 必須得「發布到網路上」。

當然，程式最終還是以 CSV 的格式在處理，
所以要直接餵給它一個 CSV 的檔案、或是一個 `java.io.Reader` 也是可以的。


支援的 output 格式
==================

* [jQuery.i18n]

亦可 extends `us.dontcareabout.i18nIsShit.generator.Generator` 自行實作。


使用方式
========

以 [jQuery.i18n] 為例：

```Java
File folder = new File();
String url = "https://docs.google.com/spreadsheets/d/e/" + PUBLISH_UID + "/pub?output=csv";

new JQuery(url).gen(folder);
```


[jQuery.i18n]: https://github.com/wikimedia/jquery.i18n
