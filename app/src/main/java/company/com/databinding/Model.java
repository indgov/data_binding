package company.com.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by ibs on 07/09/17.
 */

public class Model extends BaseObservable {
    private String value;

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(company.com.databinding.BR.value);
    }

    public Model() {
        value = "Value";
    }
}
