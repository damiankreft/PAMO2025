package com.example.BMI.ui.recommendations

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.BMI.R

class BmiChart : Fragment() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bmi_chart, container, false)
        webView = rootView.findViewById(R.id.webView)
        val webSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.defaultTextEncodingName = "utf-8"
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        val htmlContent = generateHtmlContent()
        webView.loadData(htmlContent, "text/html", "UTF-8")
        return rootView
    }

    private fun generateHtmlContent(): String = """ 
        <html>
        <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        
        function drawChart() {
        var data = google.visualization.arrayToDataTable([
        ['Day', 'BMI'],
        ["1", 24],
        ["2", 23],
        ["3", 21],
        ["4", 18]
        ]);
        
        var options = {
        title: 'BMI Changes Per Day',
        curveType: 'function',
        legend: { position: 'bottom' }
        };
        
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        }
        </script>
        </head>
        <body style="background-color: red;">
        <div id="chart_div" style="width: 100%; height: 500px;"></div>
        </body>
        </html>
    """"
}