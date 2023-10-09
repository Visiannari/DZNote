package Posts

data class AttachmentPhoto(val photo: Photo) : Attachment {
    override val type="photo"
    override fun toString(): String {
        return "$type with $photo"
    }
}
data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int
)

data class AttachmentAudio(val audio: Audio) : Attachment {
    override val type: String="audio"
}
data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean?,
    val isHd: Boolean?
)

class AttachmentVideo(val video: Video): Attachment {
    override val type: String="video"
}
data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: Array<Image> = emptyArray(),
    val firstFrame: Array<FirstFrame> = emptyArray(),
    val date: Int,
    val addingDate:Int,
    val views: Int,
    val localViews: Int,
    val comments: Int,
    val player:String,
    val platform:String,
    val canAdd:Boolean=true,
    val isPrivat:Int?,
    val accessKey:String,
    val processing:Int?,
    val isFavorite:Boolean=false,
    val canComment:Boolean=true,
    val canEdit:Boolean=true,
    val canLike:Boolean=true,
    val canRepost:Boolean=true,
    val canSubscribe:Boolean=true,
    val canAndToFaves:Boolean=true,
    val canAttachLink:Boolean=true,
    val width: Int,
    val height: Int,
    val userId: Int,
    val converting:Boolean,
    val added:Boolean,
    val isSubscribed:Boolean
)

data class Image(val height: Int, val url: String, val width: Int, val withPadding: Int?)
data class FirstFrame(val height: Int,val url: String,val width: Int)

class AttachmentFile(val file: File): Attachment {
    override val type: String="file"
}
data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size:Int,
    val ext:String,
    val url: String,
    val date: Int,
    val type:Int //1-text, 2-archive..

)

class AttachmentGift(val gift: Gift): Attachment {
    override val type: String="gift"
}
data class Gift(
    val id:Int,
    val thumb256:String,
    val thumb96:String,
    val thumb48:String
)