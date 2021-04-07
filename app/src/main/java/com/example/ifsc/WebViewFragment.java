package com.example.ifsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.Normalizer;

public class WebViewFragment extends Fragment {
    WebView myWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview,container,false);

        Bundle bundle = this.getArguments();
        String url = normalizeString(bundle.getString("url"));
        myWebView = view.findViewById(R.id.webview);
        myWebView.loadUrl("https://myifscapp.pythonanywhere.com/detail/"+url);
        //myWebView.loadUrl("https://www.ifsc.edu.br/web/campus-canoinhas/cursos");

        return view;
    }
    public String normalizeString(String string){
        return Normalizer
                .normalize(string, Normalizer.Form.NFD)
                .replaceAll("[()]","")
                .replaceAll("\\.","")
                .replaceAll(",","")
                .replaceAll("#","")
                .replaceAll("[?]","")
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[ \\t\\n\\x0B\\f\\r]","-").toLowerCase();
    }
}
