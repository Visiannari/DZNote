import Posts.Attachment
import java.util.*


interface CrudElement {
    val id: Int
    val ownerId: Int
    val date: Int
    val text: String
    //val comments<T>
}

data class Post(
    override val id: Int=0,
    override val ownerId: Int,
    override val date: Int,
    override val text: String,
    val comments: Comments?,
    val fromId: Int,
    val createdBy: Int,
    val replyOwnerId: Int,
    val friendsOnly: Boolean = false,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String = "post", //post, copy,reply,postpone,suggest
    val postSource: PostSource,
    val attachments: Array<Attachment>? = emptyArray(),
    val geo: Geo?,
    val signerId: Int?,
    val copyHistory: Array<Reposts>? = emptyArray(),
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val marketAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Donut?,
    val postponedId: Boolean = false
) : CrudElement

data class Note(
    override val id: Int=0,
    override val ownerId: Int,
    override val date: Int,
    override val text: String,
    val title:String,
    val privacy:Int,
    val commentPrivacy: Int,
    val privacyView:String,
    val privacyComment:String,
    var flagDelete:Boolean=false,
    var comments:List<CommentNote> = mutableListOf()
) : CrudElement

data class CommentNote(
    val noteId:Int,
    val commentId:Int=0,
    val ownerId:Int,
    var message: String,
    var flagDelete:Boolean=false,
    val date:Int
)



data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)
data class Comment(
    val id:Int,
    val fromId:Int,
    val text:String
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean,
    val canLikes: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(val count: Int = 0, val userReposted: Boolean = false)

data class Views(val count: Int)

data class PostSource(
    val type: String, //vk, widget,api,rss,sms
    val platform: String,//android,iphone, wphone
    val data: String,//type=vk: profileActivity,profilePhoto; type=widget: comments,like,poll
    val url: String
)

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place?
)

data class Place(
    val id: Int,
    val title: String,
    val latitude: Int,
    val longitude: Int,
    val created: Int,
    val icon: String,
    val checkins: Int,
    val updated: Int,
    val type: Int,
    val country: Int,
    val city: Int,
    val address: String
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int,
    val placeholder: Objects,
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = "all"// all, duration
)