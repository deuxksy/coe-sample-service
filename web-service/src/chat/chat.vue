<template>
  <div id="chat">
    <router-link v-for="room in rooms" v-bind:key = room.id v-bind:to="'/chat/' + room.id">
        {{room.name}}
    </router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'cors',
  data () {
    return {
      rooms: []
    }
  },
  created: function() {
    this.$http.get("/api/v1/chat/rooms")
    .then((result) => {
    this.rooms = result.data;
    })
    .catch((error) => {
    this.msg = error;
    console.log(error);
    })
  }
}
</script>

<style>
div.room {
    color: gray;
    cursor: pointer;
}
</style>
