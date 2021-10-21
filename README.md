# beduShop
## Shop app using Android studio and kotlin

For the correct use of the code I suggest to use AndroidStudio
  <a href="https://developer.android.com/studio">Android studio</a><br></br>

## About the project
The project let the user Login or register. The registration is just design, so right now you can enter any data and it would accept it.
The login is different, you must use an user from the next API <a href="https://reqres.in/api/users/">Reqres.in</a>. The password would accept any character/s.

The products that the app will show are from the same app. From this List you can click in any product to get the detail view.
From the detail view you can buy the products with the button "Agregar al carrito". That would trigger the next Fragment (The Cart).
Then if you want can add more of that product. And then checkout to buy it.


## Features

<ul>
    <li>The Home and Cart Fragment use RecyclerView</li>
    <li>The profile use geocoder to get your address (If you allow)</li>
    <li>When you buy a product It will trigger a notification (This would open the home fragment if you use it)</li>
    <li>The project use Navigation (jetpack)</li>
    <li>To fetch the url from reqres this project uses "okhttp3"</li>
    <li>To load ImagesViews from a link use Picasso</li>
</ul>