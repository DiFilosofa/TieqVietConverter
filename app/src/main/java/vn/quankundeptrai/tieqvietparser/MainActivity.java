package vn.quankundeptrai.tieqvietparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

import vn.quankundeptrai.tieqvietparser.Constants.MainConstants;
import vn.quankundeptrai.tieqvietparser.Utils.MainUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText input;
    private TextView output,shareText;
    private View convert,facebookShare, imageCreator, cancelShare, shareImg, shareStatus, copy;
    private final ArrayList<Pair<String,String>> dictionary = MainConstants.generateDictionary();
    private KeyListener keyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.originalTiengViet);
        output = findViewById(R.id.tieqVietOutput);
        convert = findViewById(R.id.convert);
        facebookShare = findViewById(R.id.facebook_share);
        cancelShare = findViewById(R.id.cancel);
        shareImg = findViewById(R.id.share_image);
        shareStatus = findViewById(R.id.share_status);
        shareText = findViewById(R.id.shareText);
        copy = findViewById(R.id.copy_result);

        keyListener = input.getKeyListener();

        imageCreator = findViewById(R.id.image_creator);
        convert.setOnClickListener(this);
        facebookShare.setOnClickListener(this);
        shareImg.setOnClickListener(this);
        shareStatus.setOnClickListener(this);
        cancelShare.setOnClickListener(this);
        copy.setOnClickListener(this);
    }

    private void enableInput(boolean enable){
        input.setEnabled(enable);
        input.setKeyListener(enable ? keyListener : null);
    }

    @Override
    public void onClick(View v) {
        String shareContent = output.getText().toString();
        switch (v.getId()){
            case R.id.convert:
                String userInput = input.getText().toString();
                if(!userInput.isEmpty()) {
                    MainUtils.startAnimation(this, convert, R.anim.rotation_0degrees);
                    output.setText(MainUtils.convertToTieqViet(userInput,dictionary));
                }
                else {
                    Toast.makeText(this, getResources().getString(R.string.emptyInput), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.facebook_share:
                if(!shareContent.isEmpty()){
                    imageCreator.setVisibility(View.VISIBLE);
                    enableInput(false);
                    shareText.setText(shareContent);
                }
                else{
                    Toast.makeText(this, getResources().getString(R.string.shareEmpty), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel:
                imageCreator.setVisibility(View.GONE);
                enableInput(true);
                break;
            case R.id.share_image:
                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(MainUtils.getBitmapFromView(shareText))
                            .build();
                    SharePhotoContent content = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();
                    new ShareDialog(this).show(content);
                    enableInput(true);
                }
                break;
            case R.id.share_status:
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    MainUtils.copy(this, shareContent);
                    Toast.makeText(this, getResources().getString(R.string.pleasePaste), Toast.LENGTH_LONG).show();
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(null)
                            .build();
                    new ShareDialog(this).show(content);
                    enableInput(true);
                }
                break;
            case R.id.copy_result:
                if(!shareContent.isEmpty()){
                    MainUtils.copy(this, shareContent);
                    Toast.makeText(this, getResources().getString(R.string.copied), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, getResources().getString(R.string.shareEmpty), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
