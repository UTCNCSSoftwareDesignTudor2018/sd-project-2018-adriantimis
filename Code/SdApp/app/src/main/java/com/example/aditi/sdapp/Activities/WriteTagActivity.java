package com.example.aditi.sdapp.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditi.sdapp.R;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created by aditi on 31/05/2018.
 */

public class WriteTagActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_tag);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter == null){
            finish();
        }

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);



    }

    @Override
    protected void onNewIntent(Intent intent){

        super.onNewIntent(intent);

        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)){

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            String username = ((EditText)findViewById(R.id.username)).getText().toString();

            if(username != null) {

                NdefMessage ndefMessage = createNdefMessage(username);
                writeNdefMessage(tag, ndefMessage);

            }

        }

    }

    @Override
    public void onResume(){

        super.onResume();

        if(nfcAdapter != null){

            if(!nfcAdapter.isEnabled()){
                Toast.makeText(this, "You must enable NFC", Toast.LENGTH_SHORT).show();
            }

            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);

        }

        //enableForegroundDispatchSystem();

    }

    @Override
    protected void onPause(){

        super.onPause();

        nfcAdapter.disableForegroundDispatch(this);

        //disableForegroundDispatchSystem();

    }

    private void formatTag(Tag tag, NdefMessage ndefMessage){

        try{

            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null){

                Toast.makeText(this, "TAG is not ndef formatable :(", Toast.LENGTH_SHORT).show();
                return;

            }

            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            Log.e("formatTag", e.getMessage());
            e.printStackTrace();
        }

    }

    private void writeNdefMessage(Tag tag, NdefMessage ndefMessage){

        try{

            if(tag == null){

                Toast.makeText(this, "Tag object cannot be null", Toast.LENGTH_SHORT).show();
                return;

            }

            Ndef ndef = Ndef.get(tag);

            if(ndef == null){

                // Format the tag adn write the message
                formatTag(tag, ndefMessage);

            }else{

                ndef.connect();

                if(!ndef.isWritable()){

                    Toast.makeText(this, "Tag must be writable", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;

                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            }


        }catch(Exception e){
            Log.e("formatTAg", e.getMessage());
            e.printStackTrace();
        }

    }

    private NdefRecord createTextRecord(String context){

        try{

            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = context.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte)(languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0, textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

    private NdefMessage createNdefMessage(String context){

        NdefRecord ndefRecord = createTextRecord(context);
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});

        return ndefMessage;

    }


}
