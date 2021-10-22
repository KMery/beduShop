# beduShop
## Shop app using Android studio and kotlin

For the correct use of the code I suggest to use <a href="https://developer.android.com/studio">Android studio</a><br></br>

<p align="center">
    <img src="https://tutorialandroid.net/wp-content/uploads/tumblr_lq9tysPJhA1ql2603o1_250.gif" alt="Can't load image :C">
</p>


## About the project
The project let the user Login or register. The registration is just design, so right now you can enter any data and it would accept it.
The login is different, you must use an user from the next API <a href="https://reqres.in/api/users/">Reqres.in</a>. The password would accept any character/s.

<p align="center">
    <img src="https://i.postimg.cc/ncX3YZpz/kt.png" alt="I couldn't find the image :C">
</p>

List of email to get a successful login:

<ul>
    <li>george.bluth@reqres.in</li>
    <li>janet.weaver@reqres.in</li>
    <li>emma.wong@reqres.in</li>
    <li>eve.holt@reqres.in</li>
    <li>charles.morris@reqres.in</li>
    <li>tracey.ramos@reqres.in</li>
    <li>michael.lawson@reqres.in</li>
    <li>lindsay.ferguson@reqres.in</li>
    <li>tobias.funke@reqres.in</li>
    <li>byron.fields@reqres.in</li>
    <li>george.edwards@reqres.in</li>
    <li>rachel.howell@reqres.in</li>
</ul>

The products that the app will show are from the <a href=" https://fakestoreapi.com/products">FakeStoreApi</a>. From this List you can click in any product to get the detail view.
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

## Links

<ul>
    <li><a href="https://material.io/">Material Design</a></li>
    <li><a href="https://developers.google.com/maps/documentation/javascript/reference/geocoder">Geocoder</a></li>
    <li><a href="https://developer.android.com/">Android developer</a></li>
    <li><a href="https://bedu.org/">Bedu</a></li>
    <li><a href="https://firebase.google.com">Firebase</a></li>
</ul>
