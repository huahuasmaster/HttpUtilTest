package zyz.com.httputiltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import zyz.com.httputiltest.http.HttpCallbackListener;
import zyz.com.httputiltest.http.HttpUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText contentEdit;
    private EditText urlEdit;
    private Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmBtn = (Button) findViewById(R.id.confirm);
        contentEdit = (EditText) findViewById(R.id.content);
        urlEdit = (EditText) findViewById(R.id.url);

        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.confirm) {
            String url = urlEdit.getText().toString().trim();
            HttpUtil.getInstance().get(url, new HttpCallbackListener() {
                @Override
                public void onFinish(final String response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contentEdit.setText(response);
                        }
                    });
                }
                @Override
                public void onError(Exception e) {
                    super.onError(e);
                    Log.i("mainactivity","出错了");
                }
            });
        }
    }
}
