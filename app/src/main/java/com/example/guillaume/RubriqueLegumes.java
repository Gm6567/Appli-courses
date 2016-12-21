package com.example.guillaume.projetmobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Created by Dorian Finel-Bacha and Guillaume Michel on 20/12/16.
 */

public class RubriqueLegumes extends Activity implements View.OnClickListener {

    private CheckBox chkCarotte, chkAvocat, chkSalade, chkPoireau, chkTomate;
    private Button btnSave;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legume);
        String listePdts = lireListeProduits("Legumes.txt");

        chkCarotte = (CheckBox)findViewById(R.id.carotte);
        chkAvocat = (CheckBox)findViewById(R.id.avocat);
        chkSalade = (CheckBox)findViewById(R.id.salade);
        chkPoireau = (CheckBox)findViewById(R.id.poireau);
        chkTomate = (CheckBox)findViewById(R.id.tomate);
        // 13.9.c Appeler la mÃ©thode majCheckBox()
        majCheckBox(listePdts);
        btnSave = (Button) findViewById(R.id.SaveLegume);
        btnSave.setOnClickListener(this);

    }

    public void majCheckBox(String tmp){
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];
        while (st.hasMoreTokens()) {
            mot[i] = st.nextToken();
            if (mot[i].equals("carotte")){
                chkCarotte.setChecked(true);
            }
            else if (mot[i].equals("salade")){
                chkSalade.setChecked(true);
            }
            else if (mot[i].equals("avocat")){
                chkAvocat.setChecked(true);
            }
            else if (mot[i].equals("poireau")){
                chkPoireau.setChecked(true);
            }
            else if (mot[i].equals("tomate")){
                chkTomate.setChecked(true);
            }
            i++;
        }

    }
    @Override
    public void onClick(View v) {
        Toast toaster;
        String msg="";

        if (chkSalade.isChecked())
            msg+="salade+";
        if (chkCarotte.isChecked())
            msg+="carotte+";
        if (chkAvocat.isChecked())
            msg+="avocat+";
        if (chkPoireau.isChecked())
            msg+="poireau+";
        if (chkTomate.isChecked())
            msg+="tomate+";
        if (!msg.equals("")){
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // 13.7.b Appeler la mÃ©thode ecrireListeLegumes()
        ecrireListeLegumes(msg, "Legumes.txt");
        // 13.8.b Appeler la mÃ©thode fermerLesLegumes()
        fermerLesLegumes();
    }

    public void ecrireListeLegumes(String tmp, String nomFic) {
        FileOutputStream fos;
        try {
            fos = openFileOutput(nomFic, Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'Ã©criture ...");
        }

    }

    public String lireListeProduits(String nomFic) {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput(nomFic);
            char[] charLus = new char[255];
            InputStreamReader isr = new InputStreamReader(fis);
            isr.read(charLus);
            data = new String(charLus);
            fis.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur de lecture ...");
        }
        return data;
    }

    public void fermerLesLegumes(){
        this.finish();
    }
}
