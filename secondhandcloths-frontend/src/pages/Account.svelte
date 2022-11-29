<script>
    import {user } from "../store";
</script>

<h1>Account Details</h1>
<img src="{$user.picture}"> 
<p>Name: {$user.name}</p>
<p>Nickname: {$user.nickname}</p>
<p>Email: {$user.email}</p>
