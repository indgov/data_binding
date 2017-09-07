package company.com.databinding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.adapters.ListenerUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import company.com.databinding.databinding.ActivityMainBinding;

@InverseBindingMethods({
        @InverseBindingMethod(
                type = CustomComponent.class,
                attribute = "value",
                method = "getValue")
})
public class MainActivity extends AppCompatActivity {
    @BindingAdapter("value")
    public static void setColor(CustomComponent view, String value) {
        if (!value.equals(view.getValue())) {
            view.setValue(value);
        }
    }

    @BindingAdapter(
            value = {"onValueChange", "valueAttrChanged"},
            requireAll = false
    )
    public static void setListeners(CustomComponent view,
                                    final ValueChangeListener onValueChangeListener,
                                    final InverseBindingListener inverseBindingListener) {
        ValueChangeListener newListener;
        if (inverseBindingListener == null) {
            newListener = onValueChangeListener;
        } else {
            newListener = new ValueChangeListener() {
                @Override
                public void onValueChange(CustomComponent view,
                                          String value) {
                    if (onValueChangeListener != null) {
                        onValueChangeListener.onValueChange(view,
                                value);
                    }
                    inverseBindingListener.onChange();
                }
            };
        }

        ValueChangeListener oldListener =
                ListenerUtil.trackListener(view, newListener,
                        R.id.textWatcher);

        if (oldListener != null) {
            view.removeListener(oldListener);
        }
        if (newListener != null) {
            view.addListener(newListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(new Model());
    }
}
