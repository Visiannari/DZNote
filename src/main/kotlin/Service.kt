interface CrudService<E> {
    fun add(element:E):E
    fun delete(id:Int):Boolean
    fun edit(element:E):Boolean
    fun read():List<E>
    fun getById(id:Int):E?
    fun restore(id:Int):Boolean
}