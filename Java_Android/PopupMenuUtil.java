package pt.greatcinema.sellapp.ui;

import gc.letsmeet.sales.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

public class PopupMenuUtil{
    private Activity activity;
    
    public final static OnClickListener DO_NOTHING_LISTENER = new OnClickListener(){
        public void onClick(DialogInterface dialog, int which){}
    };
    
    public PopupMenuUtil(Activity activity){
        this.activity = activity;
    }
    
    public AlertDialog getUserData(String title, String defaultValue, int inputType, boolean cancelable, boolean selectText, final OnUserSubmitDataListerner listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final EditText editText = new EditText(activity);
        editText.setInputType(inputType);
        editText.setText(defaultValue);
        if(selectText)
            editText.setSelection(0, editText.getText().length());
//        else editText.setSelection(editText.getText().length());
        builder.setView(editText);
        builder.setTitle(title);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(R.string.okButton, new OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                listener.userSubmitData(editText.getText().toString());
            }
        });
        if(cancelable)
            builder.setNegativeButton(R.string.cancelButton, DO_NOTHING_LISTENER);
        AlertDialog dialog = builder.create();
        dialog.setOwnerActivity(activity);
        dialog.show();
        return dialog;
    }
    
    public AlertDialog getUserData(int titleId, int defaultValueId, int inputType, boolean cancelable, boolean selectText, OnUserSubmitDataListerner listener){
        return this.getUserData(activity.getString(titleId), activity.getString(defaultValueId), inputType, cancelable, selectText, listener);
    }
    
    // SIRS project reutilization :)
    public AlertDialog showMessage(String title, String message, OnClickListener okButtonClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(R.string.okButton, okButtonClickListener);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setOwnerActivity(activity);
        dialog.show();
        return dialog;
    }
    
    public AlertDialog showMessage(int titleId, int messageId, OnClickListener okButtonClickListener){
        return showMessage(activity.getString(titleId), activity.getString(messageId), okButtonClickListener);
    }
    
    public AlertDialog showMessage(String title, String message){
        return showMessage(title, message, new OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.dismiss();
            }
        });
    }
    
    public AlertDialog showMessage(int titleId, int messageId){
        return showMessage(activity.getString(titleId), activity.getString(messageId));
    }
    
    public void getConfirmationMessage(String title, String message, OnClickListener positive, OnClickListener negative){
        if(positive == null)
            positive = DO_NOTHING_LISTENER;
        if(negative == null)
            negative = DO_NOTHING_LISTENER;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(R.string.yesButton, positive);
        builder.setNegativeButton(R.string.noButton, negative);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setOwnerActivity(activity);
        dialog.show();
    }
    
    public void getConfirmationMessage(int titleId, int messageId, OnClickListener positive, OnClickListener negative){
        getConfirmationMessage(activity.getString(titleId), activity.getString(messageId), positive, negative);
    }
    
    public interface OnUserSubmitDataListerner{
        public void userSubmitData(String data);
    }
}
