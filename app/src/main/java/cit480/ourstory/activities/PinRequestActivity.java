package cit480.ourstory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import cit480.ourstory.R;



public class PinRequestActivity extends AppCompatActivity {

    public String e;
    public String ans;


    public void Randomize(){

        String[] words = {
                " zero",
                " one",
                " two",
                " three",
                " four",
                " five",
                " six",
                " seven",
                " eight",
                " nine",
        };

        Random rand = new Random();
        int n = rand.nextInt(9) + 1;
        String a = words[n];

        Random rand2 = new Random();
        int m = rand2.nextInt(9) + 1;
        String b = words[m];

        Random rand3 = new Random();
        int o = rand3.nextInt(9) + 1;
        String c = words[o];

        Random rand4 = new Random();
        int p = rand4.nextInt(9) + 1;
        String d = words[p];

        e = a + b + c + d;

        ans = "" + n + m + o + p;

        TextView text3 = (TextView)findViewById(R.id.textView3);
        text3.setText(e);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentmode_layout);

        Randomize();

        Button confirm  = (Button) findViewById(R.id.button2);
        Button randomize = (Button) findViewById(R.id.button);

        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView text2 = (EditText)findViewById(R.id.editText2);
                String otherans =  text2.getText().toString().trim();


                int a = Integer.parseInt(ans);
                int b = Integer.parseInt(otherans);

                if (a == b){
                    Toast toast = Toast.makeText(getApplicationContext(), "Authenticated!", Toast.LENGTH_SHORT);
                    toast.show();


                    Intent intent = new Intent(PinRequestActivity.this, FragmentTabActivityParentMode.class);
                    startActivity(intent);

                } else {

                    text2.setText("");
                    Randomize();
                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect pin. Please try again!", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        });


        randomize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Randomize();
            }
        });

    }

}

