package com.hermanowicz.badmintonschool.models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hermanowicz.badmintonschool.R;
import com.hermanowicz.badmintonschool.utils.EmailManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactModel {

    public final int MESSAGE_LONG_LIMIT = 1000;
    public final int SUBJECT_LONG_LIMIT = 40;

    private final Context context;

    public ContactModel(Context context) {
        this.context = context;
    }

    public void sendEmail(String emailAddress, String name, String subject, String message) {
        String mailApiUrl = context.getString(R.string.mail_php_api);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("from_email_address", emailAddress);
        params.put("from_name", name);
        params.put("subject", subject);
        params.put("message", message);

        JsonObjectRequest request_json = new JsonObjectRequest(mailApiUrl, new JSONObject(params),
                response -> {
                }, error -> VolleyLog.e("Blad", error.getMessage()));
        EmailManager.getInstance().addEmailToRequestQueue(request_json);
    }

    public boolean isEmailAddressValid(String email) {
        String emailFormat =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(emailFormat,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isFieldEmpty(@NonNull String string){
        return string.length() < 1;
    }

    public boolean isFieldTooLong(@NonNull String string, int limit) {
        return string.length() > limit;
    }
}