package Posts

import Comment
import Comments
import Copyright
import CrudService
import Likes
import Post
import PostSource
import Reposts
import Views


fun main() {
    val post = Post(
        ownerId = 1,
        fromId = 1,
        createdBy = 1,
        date = 4325,
        text = "21 июня самый длинный световой день",
        replyOwnerId = 1,
        comments = Comments(canClose = false, canOpen = false),
        copyright = Copyright(1, "www.kkk.ru", "name", "type"),
        likes = Likes(userLikes = true),
        reposts = Reposts(count = 1),
        views = Views(0),
        postType = "copy",
        postSource = PostSource("vk", "android", "profileActivity", "URL"),
        attachments = arrayOf(
            AttachmentAudio(
                Audio(
                    1,
                    1,
                    "author",
                    "title",
                    4,
                    "ljk",
                    1,
                    1,
                    1,
                    7,
                    null,
                    null
                )
            )
        ),
        geo = null,
        signerId = null,
        copyHistory = null,
        canPin = false,
        donut = null
    )
    val post2 = Post(
        ownerId = 1,
        fromId = 1,
        createdBy = 1,
        date = 4325,
        text = "21 июня самый длинный световой день",
        replyOwnerId = 1,
        comments = Comments(canClose = false, canOpen = false),
        copyright = Copyright(1, "www.kkk.ru", "name", "type"),
        likes = Likes(userLikes = true),
        reposts = Reposts(count = 1),
        views = Views(0),
        postType = "copy",
        postSource = PostSource("vk", "android", "profileActivity", "URL"),
        attachments = arrayOf(AttachmentGift(Gift(1, "a", "a", "a"))),
        geo = null,
        signerId = null,
        copyHistory = null,
        canPin = false,
        donut = null
    )
    val postUpdate = Post(
        id = 2,
        ownerId = 1,
        fromId = 1,
        createdBy = 1,
        date = 4325,
        text = "23 июня самый длинный световой день",
        replyOwnerId = 1,
        comments = Comments(canClose = false, canOpen = false),
        copyright = Copyright(1, "www.kkk.ru", "name", "type"),
        likes = Likes(userLikes = true),
        reposts = Reposts(count = 1),
        views = Views(0),
        postType = "copy",
        postSource = PostSource("vk", "android", "profileActivity", "URL"),
        attachments = arrayOf(AttachmentGift(Gift(1, "a", "a", "a"))),
        geo = null,
        signerId = null,
        copyHistory = null,
        canPin = false,
        donut = null
    )
    val comment= Comment(1, 2, "good")
    val service = WallService
    println(WallService.add(post))
    println(WallService.add(post2))
    println(WallService.edit(postUpdate))
    println(WallService.getById(1))
    println(WallService.getById(1)?.attachments?.get(0))
    WallService.createComment(1, comment)
    println(WallService.getById(1)?.comments)
}

class PostNotFoundException(message: String) : RuntimeException(message)
object WallService : CrudService<Post> {
    private var posts = emptyArray<Post>()
    private var lastId: Int = 0
    private var comments = emptyArray<Comment>()


    override fun add(post: Post): Post {
        posts += post.copy(
            id = lastId++,
            comments = post.comments?.copy(),
            copyright = post.copyright.copy(),
            likes = post.likes.copy(),
            reposts = post.reposts.copy(),
            views = post.views.copy(),
            postSource = post.postSource.copy(),
            geo = post.geo?.copy(),
            donut = post.donut?.copy()
        )
        return posts.last()
    }

    override fun edit(post: Post): Boolean {
        for ((index, postArr) in posts.withIndex()) {
            if (postArr.id == post.id) {
                posts[index] = post.copy(
                    id = lastId++,
                    comments = post.comments?.copy(),
                    copyright = post.copyright.copy(),
                    likes = post.likes.copy(),
                    reposts = post.reposts.copy(),
                    views = post.views.copy(),
                    postSource = post.postSource.copy(),
                    geo = post.geo?.copy(),
                    donut = post.donut?.copy()
                )
                return true
            }
        }
        return false
    }

    override fun delete(id: Int):Boolean {
        TODO("Not yet implemented")
    }

    override fun read(): List<Post> {
        TODO("Not yet implemented")
    }

   override fun getById(id: Int): Post? {
        for (post in posts) {
            if (post.id == id) {
                return post
            }
        }
        return null
    }

    override fun restore(id: Int):Boolean {
        TODO("Not yet implemented")
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        if (getById(postId) != null) {
            comments += comment.copy()
            return comments.last()
        } else {
            throw PostNotFoundException("No post with $postId")
        }
    }
    fun clear() {
        posts = emptyArray()
        comments= emptyArray()
        lastId = 0
    }
}