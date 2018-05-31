package com.example.aditi.sdapp.Activities;

import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditi.sdapp.R;
import com.example.aditi.sdapp.ViewModels.StaffViewModel;
import com.example.aditi.sdapp.ViewModels.UserProfileViewModel;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class StaffActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    private StaffViewModel staffViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        staffViewModel = ViewModelProviders.of(this).get(StaffViewModel.class);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter == null){

            Toast.makeText(this, "NFC not available", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        //nfcAdapter.setNdefPushMessageCallback(this, this);

        StaffViewModel staffViewModel = ViewModelProviders.of(this).get(StaffViewModel.class);
        staffViewModel.init(getIntent().getExtras().get("username").toString());

        staffViewModel.getStaff().observe(this, staff -> {

            // Update changes in the ui

        });

        staffViewModel.getCheckedLines().observe(this, checkedLines -> {

            // Update the ui with the lines checked
            if(checkedLines.size() == 2) {

                ((TextView) findViewById(R.id.primaLinieVerificata)).setText("Line 1 - " + checkedLines.get(0).getLineNumber());
                ((TextView) findViewById(R.id.aDouaLinieVerificata)).setText("Line 2 - " + checkedLines.get(1).getLineNumber());

            }else{

                Toast.makeText(this, "Invalid bus pass", Toast.LENGTH_SHORT).show();

            }

        });

        Button writeToTagButton = findViewById(R.id.writeToTAgButton);

        writeToTagButton.setOnClickListener(view -> {

            // Start a new activity

            Intent intent = new Intent(this, WriteTagActivity.class);

            startActivity(intent);

        });

    }

    @Override
    protected void onNewIntent(Intent intent){

        super.onNewIntent(intent);

        //resolveIntent(intent);
        readFromTag(intent);

//        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)){
//
//            Toast.makeText(this, "NfcIntent!", Toast.LENGTH_SHORT).show();
//
//            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            NdefMessage ndefMessage = createNdefMessage("adi");
//
//            writeNdefMessage(tag, ndefMessage);
//
//        }

    }

    private void readFromTag(Intent intent){

        if(intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){

            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(rawMessages != null){

                NdefMessage[] messages = new NdefMessage[rawMessages.length];

                for(int i = 0; i < rawMessages.length; i++){
                    messages[i] = (NdefMessage)rawMessages[i];
                }

                for(int i = 0; i < messages.length; i++){

                    NdefRecord[] records = messages[i].getRecords();

                    for(int j = 0; j < records.length; j++){

                        Toast.makeText(this, new String(records[i].getPayload()).substring(3), Toast.LENGTH_SHORT).show();
                        String username = new String(records[i].getPayload()).substring(3);

                        staffViewModel.checkPassValidity(username);

                    }

                }

            }

        }

    }

    private void resolveIntent(Intent intent){

        Tag tag = (Tag)intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        NdefMessage ndefMessage = createNdefMessage("adi");

        writeNdefMessage(tag, ndefMessage);

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

    private void processIntent(Intent intent){

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage msg = (NdefMessage)rawMsgs[0];
        // The data is in  msg.get5Records()[0].getPayload();

    }

//    private void enableForegroundDispatchSystem(){
//
//        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        IntentFilter[] intentFilters = new IntentFilter[] {};
//
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
//
//    }

//    private void disableForegroundDispatchSystem(){
//
//        nfcAdapter.disableForegroundDispatch(this);
//
//    }

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
