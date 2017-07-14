package br.com.rodrigo.agendinha.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.rodrigo.agendinha.R;
import br.com.rodrigo.agendinha.dao.AlunoDAO;

/**
 * Created by Rodrigo on 8/12/2016.
 */
public class SMSReceiver extends BroadcastReceiver{
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if (dao.ehAluno(telefone)){
            Toast.makeText(context, "Chegou um SMS!", Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
        dao.close();
    }
}
