package id.apigo.starter;

import android.os.Parcel;
import android.os.Parcelable;

import com.mesosfer.MesosferObject;

/**
 * Created by Eyro on 02/10/17.
 */

public class Param<T extends MesosferObject> implements Parcelable {
    private Class<T> aClass;
    private boolean select;
    private boolean create;
    private boolean update;
    private boolean delete;

    public Param() {
        this.select = true;
        this.create = true;
        this.update = true;
        this.delete = true;
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public Param setaClass(Class<T> aClass) {
        this.aClass = aClass;
        return this;
    }

    public boolean isSelect() {
        return select;
    }

    public Param setSelect(boolean select) {
        this.select = select;
        return this;
    }

    public boolean isCreate() {
        return create;
    }

    public Param setCreate(boolean create) {
        this.create = create;
        return this;
    }

    public boolean isUpdate() {
        return update;
    }

    public Param setUpdate(boolean update) {
        this.update = update;
        return this;
    }

    public boolean isDelete() {
        return delete;
    }

    public Param setDelete(boolean delete) {
        this.delete = delete;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.aClass);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
        dest.writeByte(this.create ? (byte) 1 : (byte) 0);
        dest.writeByte(this.update ? (byte) 1 : (byte) 0);
        dest.writeByte(this.delete ? (byte) 1 : (byte) 0);
    }

    protected Param(Parcel in) {
        //noinspection unchecked
        this.aClass = (Class<T>) in.readSerializable();
        this.select = in.readByte() != 0;
        this.create = in.readByte() != 0;
        this.update = in.readByte() != 0;
        this.delete = in.readByte() != 0;
    }

    public static final Creator<Param> CREATOR = new Creator<Param>() {
        @Override
        public Param createFromParcel(Parcel source) {
            return new Param(source);
        }

        @Override
        public Param[] newArray(int size) {
            return new Param[size];
        }
    };
}
