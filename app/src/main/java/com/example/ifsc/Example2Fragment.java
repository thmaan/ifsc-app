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

public class Example2Fragment extends Fragment {
    WebView myWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exemplo2 ,container,false);

        Bundle bundle = this.getArguments();
        String url = normalizeString(bundle.getString("url"));
        Toast.makeText(getActivity(), ""+url, Toast.LENGTH_SHORT).show();
        myWebView = view.findViewById(R.id.webview);
        //myWebView.loadUrl("http://myifscapp.pythonanywhere.com/detail/"+url);
        myWebView.loadUrl("https://www.ifsc.edu.br/web/campus-canoinhas/cursos");

        return view;
    }
    public String normalizeString(String valorAcentuado){
        return Normalizer
                .normalize(valorAcentuado, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[ \\t\\n\\x0B\\f\\r]","-").toLowerCase();
    }
}
