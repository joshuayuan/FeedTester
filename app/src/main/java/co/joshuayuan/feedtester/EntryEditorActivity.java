package co.joshuayuan.feedtester;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EntryEditorActivity extends AppCompatActivity {

    EditText priceEdit;
    EditText descriptionEdit;
    ImageView imageEdit;
    private static final int entryRequestcode = 642;
    private static final int resultOK = 585;
    private static final int cameraRequestCode = 652;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_editor);

        priceEdit = (EditText)findViewById(R.id.entry_priceTag_editor);
        descriptionEdit = (EditText) findViewById(R.id.entry_description_editor);
        imageEdit = (ImageView) findViewById(R.id.entry_image_editor);

        Button saveButton = (Button)findViewById(R.id.save_button);
        if(saveButton!=null){

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Entry newEntry = new Entry(((BitmapDrawable)imageEdit.getDrawable()).getBitmap(), "NEW USER",priceEdit.getText().toString(), descriptionEdit.getText().toString() );
                    Intent intent = new Intent(EntryEditorActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("newEntry", newEntry);
                    intent.putExtras(bundle);

                    setResult(resultOK, intent);
                    finish();
                }
            });
        }
        if (imageEdit!=null){
            imageEdit.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, cameraRequestCode);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==cameraRequestCode){
            if (resultCode==RESULT_OK){

                Bitmap photo = (Bitmap)data.getExtras().get("data");
                imageEdit.setImageBitmap(photo);

            } else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Image capture failed (canceled).", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Image capture failed.", Toast.LENGTH_LONG).show();

            }

        }
    }
}
