package company.com.databinding;

import android.content.Context;
import android.databinding.Bindable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;


public class CustomComponent extends ConstraintLayout {
    private String value;
    private EditText txt;
    private TextWatcher textWatcher;
    ValueChangeListener listener;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (txt != null) {
            txt.setText(value);
        }
    }

    public CustomComponent(Context context) {
        super(context);
        init(context);
    }

    public CustomComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context) {

    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.custom_component, this);
        txt = findViewById(R.id.txt_box);
        final CustomComponent self = this;
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (listener != null) {
                    listener.onValueChange(self, editable.toString());
                }
            }
        };
        txt.addTextChangedListener(textWatcher);
    }

    public void addListener(ValueChangeListener listener) {
        this.listener = listener;
    }

    public void removeListener(ValueChangeListener listener) {
        this.listener = null;
    }
}

