package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_reference_book")
public class ReferenceBookEntity {

    @ColumnInfo(name = "book_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mBookId;

    @ColumnInfo(name = "book_name")
    private String mBookName;

    @ColumnInfo(name = "book_thumb")
    private String mBookThumbnail;

    @Ignore
    public ReferenceBookEntity(String mBookName) {
        this.mBookName = mBookName;
    }

    public ReferenceBookEntity(@NonNull Long mBookId, String mBookName, String mBookThumbnail) {
        this.mBookId = mBookId;
        this.mBookName = mBookName;
        this.mBookThumbnail = mBookThumbnail;
    }

    @NonNull
    public Long getBookId() {
        return mBookId;
    }

    public void setBookId(@NonNull Long mBookId) {
        this.mBookId = mBookId;
    }

    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public String getBookThumbnail() {
        return mBookThumbnail;
    }

    public void setBookThumbnail(String mBookThumbnail) {
        this.mBookThumbnail = mBookThumbnail;
    }
}
