package com.example.ativ01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private LinearLayout dynamicLayout;
    private int entryCount = 0; // Contador de entradas no dynamic_layout
    private List<TextView> originalTextViewList = new ArrayList<>(); // Lista para armazenar TextViews na ordem de inserção

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        dynamicLayout = findViewById(R.id.dynamic_layout);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar se já foram adicionadas 4 entradas
                if (entryCount < 4) {
                    // Recuperar o texto digitado
                    String texto = editText.getText().toString();

                    // Criar um novo TextView para exibir o texto
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(texto);

                    // Adicionar o TextView à lista originalTextViewList
                    originalTextViewList.add(textView);

                    // Adicionar o TextView ao dynamic_layout
                    dynamicLayout.addView(textView);

                    editText.setText("");

                    // Incrementar o contador de entradas
                    entryCount++;
                }
            }
        });

        Button buttonDel = findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpar o dynamic_layout
                dynamicLayout.removeAllViews();

                // Limpar a lista originalTextViewList
                originalTextViewList.clear();

                // Resetar o contador de entradas
                entryCount = 0;
            }
        });

        Button buttonInser = findViewById(R.id.buttonInser);
        buttonInser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reorganizar as palavras na ordem de inserção
                reorderDynamicLayout();
            }
        });

        Button buttonCres = findViewById(R.id.buttonCres);
        buttonCres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ordenar as palavras em ordem crescente
                sortDynamicLayout(true);
            }
        });

        Button buttonDec = findViewById(R.id.buttonDec);
        buttonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ordenar as palavras em ordem decrescente
                sortDynamicLayout(false);
            }
        });
    }

    // Método para ordenar o dynamic_layout
    private void sortDynamicLayout(final boolean ascending) {
        // Criar uma lista para armazenar os TextViews
        List<TextView> textViewList = new ArrayList<>(originalTextViewList);

        // Ordenar a lista de TextViews
        Collections.sort(textViewList, new Comparator<TextView>() {
            @Override
            public int compare(TextView textView1, TextView textView2) {
                String text1 = textView1.getText().toString();
                String text2 = textView2.getText().toString();
                if (ascending) {
                    return text1.compareTo(text2);
                } else {
                    return text2.compareTo(text1);
                }
            }
        });

        // Limpar o dynamic_layout
        dynamicLayout.removeAllViews();

        // Adicionar os TextViews ordenados de volta ao dynamic_layout
        for (TextView textView : textViewList) {
            dynamicLayout.addView(textView);
        }
    }

    // Método para reorganizar o dynamic_layout na ordem de inserção
    private void reorderDynamicLayout() {
        // Limpar o dynamic_layout
        dynamicLayout.removeAllViews();

        // Adicionar os TextViews de volta ao dynamic_layout na ordem original
        for (TextView textView : originalTextViewList) {
            dynamicLayout.addView(textView);
        }
    }
}