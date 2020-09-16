package com.edvardas.amf3


class UnexpectedDataException : Exception {
    /**
     * Creates [UnexpectedDataException] without a message
     */
    constructor()

    /**
     * Creates [UnexpectedDataException] with a specified message
     *
     * @param text The specified message. The detail is saved for later retrieval by [message] property
     */
    constructor(text: String) : super(text)
}