package com.kirdmt.simpleplayer.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/*
{"wrapperType":"track", "kind":"song", "artistId":55279422, "collectionId":76144717, "trackId":76144538, "artistName":"Foxy",
"collectionName":"Get Off", "trackName":"Get Off", "collectionCensoredName":"Get Off", "trackCensoredName":"Get Off",
"artistViewUrl":"https://music.apple.com/us/artist/foxy/55279422?uo=4",
"collectionViewUrl":"https://music.apple.com/us/album/get-off/76144717?i=76144538&uo=4",
"trackViewUrl":"https://music.apple.com/us/album/get-off/76144717?i=76144538&uo=4",
"previewUrl":"https://audio-ssl.itunes.apple.com/itunes-assets/Music/eb/27/17/mzm.zwyofufm.aac.p.m4a",
 "artworkUrl30":"https://is5-ssl.mzstatic.com/image/thumb/Music/v4/1b/10/ec/1b10ec8c-7758-f25e-82b0-2a68c2cf4b74/source/30x30bb.jpg",
 "artworkUrl60":"https://is5-ssl.mzstatic.com/image/thumb/Music/v4/1b/10/ec/1b10ec8c-7758-f25e-82b0-2a68c2cf4b74/source/60x60bb.jpg",
 "artworkUrl100":"https://is5-ssl.mzstatic.com/image/thumb/Music/v4/1b/10/ec/1b10ec8c-7758-f25e-82b0-2a68c2cf4b74/source/100x100bb.jpg",
 "collectionPrice":6.99, "trackPrice":1.29, "releaseDate":"1978-01-01T12:00:00Z",
 "collectionExplicitness":"notExplicit", "trackExplicitness":"notExplicit", "discCount":1, "discNumber":1, "trackCount":8,
  "trackNumber":5, "trackTimeMillis":244166, "country":"USA", "currency":"USD", "primaryGenreName":"R&B/Soul", "isStreamable":true}*/
data class Track(
    val wrapperType: String,
    val kind: String,
    val artistId: String,
    val collectionId: String,
    val trackId: String,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val collectionCensoredName: String,
    val trackCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val trackViewUrl: String,
    val previewUrl: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: String,
    val trackPrice: String,
    val releaseDate: String,
    val collectionExplicitness: String,
    val trackExplicitness: String,
    val discCount: String,
    val discNumber: String,
    val trackCount: String,
    val trackNumber: String,
    val trackTimeMillis: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val isStreamable: String
)

@Parcelize
data class TrackSearchResult (val trackName: String, val artistName: String, val artworkUrl100: String, val previewUrl: String) :
    Parcelable

data class TrackSearchResults (val results : List<TrackSearchResult>)
{
    fun getList() = results
}