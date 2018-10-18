# TumblrReader

This is app showing lists of post from twitterthecomic Tumblrs account. The app is adjusted to show correctly video and picture posts.

## Architecture
The app is written using MVP architecture.
The main drive behind this was to make app as lightweight and easy to read as posible. That's why is no DI container and dependencies are injected to Presenter constructor in MainActivity.

## Used libraries
* RxJava 2 (with add-ons)
* Retrofit (with add-ons)
* Picasso
* Mockito

## ToDo
- [ ] Add UI tests
- [ ] Add progress bar
- [ ] Add paging
- [ ] Add caching
