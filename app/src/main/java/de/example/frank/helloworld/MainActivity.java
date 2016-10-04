package de.example.frank.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView n;
    private Button b;
    private EditText eingabe;
    private ListView anrede;
    private ArrayAdapter<String> adapter;
    private boolean firstClick = true;
    public double doubleValue;
    private String ar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n= (TextView) findViewById(R.id.nachricht);
        b= (Button) findViewById(R.id.weiter);
        eingabe=(EditText) findViewById(R.id.eingabe);
        anrede= (ListView)findViewById(R.id.anrede);
        adapter = new ArrayAdapter<String>(this,android.R.layout.
                simple_list_item_1,getResources().getStringArray(R.array.anreden));
        anrede.setAdapter(adapter);

        anrede.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ar= adapter.getItem(i) + " ";
                anrede.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                anrede.setSelector(android.R.color.darker_gray);



//                anrede.setVisibility(View.INVISIBLE);
            }
        });

        n.setText(R.string.hallo2);
        b.setText(R.string.btntext);
        eingabe.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    try {
                        doubleValue = Double.parseDouble(editable.toString().replace(',', '.'));
                        doubleValue*=2;

                    } catch (NumberFormatException e) {
                        //Error
                    }
                }

                    b.setEnabled(editable.length()>0);

            }
        });



        b.setEnabled(false);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if(firstClick) {
                    eingabe.setText( Double.toString(doubleValue).replace('.',','));
                    n.setText(getString(R.string.hallo,ar + eingabe.getText()));
                    eingabe.setVisibility(View.INVISIBLE);
                    anrede.setVisibility(View.INVISIBLE);
                    firstClick = false;
                }else
                    finish();
            }
        });
    }
}
