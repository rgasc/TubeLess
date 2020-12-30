package nl.rgasc.tubeless.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * This class is used to parse the XML response of the RSS feed. The format is as follows:
 *
 * - feed
 *      - entry
 *          uploaded - published
 *          - author
 *              channelName - name
 *          - group
 *              title - title
 *              - content
 *                  videoUrl - url#
 *                  - thumbnail
 *                      thumbnailUrl - url#
 *                  - community
 *                      - statistics
 *                          views - views#
 *
 * '#' - means this is an attribute
 */
class RSSResponse {

    @Root(name = "feed", strict = false)
    class Feed @JvmOverloads constructor(
        @field: ElementList(inline = true)
        var entryList: List<Entry>? = null
    )

    @Root(name = "entry", strict = false)
    class Entry @JvmOverloads constructor(
        @field: Element(name = "published")
        var uploaded: String = "",
        @field: Element(name = "author")
        var author: Author? = null,
        @field: Element(name = "group")
        var group: Group? = null
    )

    @Root(name = "author", strict = false)
    class Author @JvmOverloads constructor(
        @field: Element(name = "name")
        var channelName: String = ""
    )

    @Root(name = "group", strict = false)
    class Group @JvmOverloads constructor(
        @field: Element(name = "title")
        var title: String = "",
        @field: Element(name = "content")
        var content: Content? = null,
        @field: Element(name = "thumbnail")
        var thumbnail: Thumbnail? = null,
        @field: Element(name = "community")
        var community: Community? = null
    )

    @Root(name = "content", strict = false)
    class Content @JvmOverloads constructor(
        @field: Attribute(name = "url")
        var videoUrl: String = ""
    )

    @Root(name = "thumbnail", strict = false)
    class Thumbnail @JvmOverloads constructor(
        @field: Attribute(name = "url")
        var thumbnailUrl: String = ""
    )

    @Root(name = "community", strict = false)
    class Community @JvmOverloads constructor(
        @field: Element(name = "statistics")
        var statistics: Statistics? = null
    )

    @Root(name = "statistics", strict = false)
    class Statistics @JvmOverloads constructor(
        @field: Attribute(name = "views")
        var views: String = ""
    )
}