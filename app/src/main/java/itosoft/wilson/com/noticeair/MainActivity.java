package itosoft.wilson.com.noticeair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RequestQueue requestQueue;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview2);
        requestQueue = Volley.newRequestQueue(this);
        imageView = (ImageView) findViewById(R.id.imageView);

        String url = "http://api.inder.gov.co:8080/uploads/noticias/20161115162306582b7cba3e5ad.jpg";

        Glide.with(this)
                .load(url)
                .centerCrop()
                .crossFade()
                .thumbnail(0.5f)
                .into(imageView);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://api.inder.gov.co/api/v1/noticias",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String imagen_url = item.getString("imagen_url");
                                String texto = item.getString("texto");


//                                    Glide.with(Context).load(imagen_url).centerCrop().crossFade().thumbnail(0.5f).into(imageView);

                                textView.append(imagen_url + "\n" + "\n" + texto + "\n" + "\n" + "\n" + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", "ERROR");
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
