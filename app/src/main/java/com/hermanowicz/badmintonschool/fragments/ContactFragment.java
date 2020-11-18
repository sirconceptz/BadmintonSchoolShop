package com.hermanowicz.badmintonschool.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hermanowicz.badmintonschool.R;
import com.hermanowicz.badmintonschool.models.ContactModel;

public class ContactFragment extends Fragment {

    private ContactModel model;

    private EditText clientName, clientEmailAddress, emailSubject, emailMessage;
    private TextView charCounter;
    private Button sendMessageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        model = new ContactModel(getContext());
        initView(view);
        setListeners();
        setViews();

        return view;
    }

    private void onClickSendEmail() {
        String name = clientName.getText().toString();
        String address = clientEmailAddress.getText().toString();
        String subject = emailSubject.getText().toString();
        String message = emailMessage.getText().toString();

        if(model.isFieldEmpty(name))
            showErrorNoFillName();
        else if(model.isFieldEmpty(address))
            showErrorNoFillEmailAddress();
        else if(!model.isEmailAddressValid(address))
            showErrorInvalidEmailFormat();
        else if(model.isFieldEmpty(subject))
            showErrorNoFillSubject();
        else if(model.isFieldTooLong(subject, model.SUBJECT_LONG_LIMIT))
            showErrorSubjectTooLong();
        else if(model.isFieldEmpty(message))
            showErrorNoFillMessage();
        else if(model.isFieldTooLong(message, model.MESSAGE_LONG_LIMIT))
            showErrorMessageTooLong();
        else {
            model.sendEmail(name, address, subject, message);
            emailSentSuccessful();
        }
    }

    private void onTextChangeSubject() {
        String subject = emailSubject.getText().toString();

        if(model.isFieldTooLong(subject, model.SUBJECT_LONG_LIMIT))
            emailSubject.setError(getString(R.string.contact_error_email_subject_too_long));
    }

    private void onTextChangeMessage() {
        String message = emailMessage.getText().toString();
        int messageLenght = message.length();

        updateMessageCharCounter(messageLenght, model.MESSAGE_LONG_LIMIT);
        if(model.isFieldTooLong(message, model.MESSAGE_LONG_LIMIT))
            emailMessage.setError(getString(R.string.contact_error_email_message_too_long));
    }

    private void clearFields() {
        clientName.getText().clear();
        clientEmailAddress.getText().clear();
        emailSubject.getText().clear();
        emailMessage.getText().clear();
    }

    public void showErrorNoFillName() {
        clientName.setError(getText(R.string.contact_error_no_name));
        Toast.makeText(getContext(), getText(R.string.contact_error_no_name), Toast.LENGTH_SHORT).show();
    }

    public void showErrorNoFillEmailAddress() {
        clientEmailAddress.setError(getText(R.string.contact_error_no_email_address));
        Toast.makeText(getContext(), getText(R.string.contact_error_no_email_address), Toast.LENGTH_SHORT).show();
    }

    public void showErrorNoFillSubject() {
        emailSubject.setError(getText(R.string.contact_error_no_email_subject));
        Toast.makeText(getContext(), getText(R.string.contact_error_no_email_subject), Toast.LENGTH_SHORT).show();
    }

    public void showErrorNoFillMessage() {
        emailMessage.setError(getText(R.string.contact_error_no_email_message));
        Toast.makeText(getContext(), getText(R.string.contact_error_no_email_message), Toast.LENGTH_SHORT).show();
    }

    public void showErrorInvalidEmailFormat() {
        clientEmailAddress.setError(getText(R.string.contact_invalid_email_address_format));
        Toast.makeText(getContext(), getText(R.string.contact_invalid_email_address_format), Toast.LENGTH_SHORT).show();
    }

    public void showErrorSubjectTooLong() {
        emailMessage.setError(getText(R.string.contact_error_email_subject_too_long));
        Toast.makeText(getContext(), getText(R.string.contact_error_email_subject_too_long), Toast.LENGTH_SHORT).show();
    }

    public void showErrorMessageTooLong() {
        emailMessage.setError(getText(R.string.contact_error_email_message_too_long));
        Toast.makeText(getContext(), getText(R.string.contact_error_email_message_too_long), Toast.LENGTH_SHORT).show();
    }

    public void emailSentSuccessful() {
        Toast.makeText(getContext(), getText(R.string.contact_email_sent_successful), Toast.LENGTH_SHORT).show();
        clearFields();
    }

    public void updateMessageCharCounter(int counter, int longLimit) {
        charCounter.setText(String.format("%s: %d/%d", getText(R.string.contact_char_counter).toString(), counter, longLimit));
    }

    private void initView(View view) {
        sendMessageButton = view.findViewById(R.id.buttonSendEmail);
        charCounter = view.findViewById(R.id.charCounter);
        clientName = view.findViewById(R.id.name);
        clientEmailAddress = view.findViewById(R.id.address);
        emailSubject = view.findViewById(R.id.subject);
        emailMessage = view.findViewById(R.id.message);
    }

    private void setListeners() {
        sendMessageButton.setOnClickListener(v ->
                onClickSendEmail());

        emailSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                onTextChangeSubject();
            }
        });

        emailMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                onTextChangeMessage();
            }
        });
    }

    private void setViews() {
        charCounter.setText(String.format("%s: 0/%d", getText(R.string.contact_char_counter).toString(), model.MESSAGE_LONG_LIMIT));
    }
}