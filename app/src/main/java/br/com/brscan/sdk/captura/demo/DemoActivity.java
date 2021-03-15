package br.com.brscan.sdk.captura.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.brscan.sdk.captura.CapturaActivity;

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = "BrScanSDKDemoActivity";
    private static final int RETORNO_CAPTURA_ACTIVITY = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1001);

        ImageView capturaDocumento = findViewById(R.id.capturaDocumento);
        capturaDocumento.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    iniciaCaptura();
                    return true;
                }

                return true;
            }
        });

    }

    private void iniciaCaptura() {
        Intent i = new Intent(DemoActivity.this, CapturaActivity.class);
        i.putExtra("chave", "");
        i.putExtra("cropDocumento", true);
        i.putExtra("validaDocumento", true);
        i.putExtra("timeoutCapturaManual", 10000);
        startActivityForResult(i, RETORNO_CAPTURA_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case (RETORNO_CAPTURA_ACTIVITY) : {
                if(resultCode == CapturaActivity.SUCESSO_CAPTURA_IMAGEM) {
                    String arquivo = data.getStringExtra("arquivo");
                    Bitmap imageBitmap = BitmapFactory.decodeFile(arquivo);

                    LinearLayout capturaDocumentoNovo = findViewById(R.id.capturaDocumentoNovo);
                    capturaDocumentoNovo.setVisibility(View.VISIBLE);

                    ImageView imagemCapturada = findViewById(R.id.imagemCapturada);
                    imagemCapturada.setImageBitmap(imageBitmap);

                    String tipo = data.getStringExtra("tipo");
                    TextView tipoTexto = findViewById(R.id.tipoTexto);

                    if(tipo != null && tipo != "") {
                        tipoTexto.setText("Tipo: "+tipo);
                    }
                    else {
                        tipoTexto.setText("");
                    }

                    String score = data.getStringExtra("score");

                    TextView scoreTexto = findViewById(R.id.scoreTexto);
                    if(score != null && score != "") {
                        scoreTexto.setText("Score: "+score);
                    }
                    else {
                        scoreTexto.setText("");
                    }

                    Log.d("brscansdk", "Tipo de captura:" + data.getStringExtra("tipoCaptura"));
                }
                else {
                    String erro = data.getStringExtra("erro");
                    Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }
}