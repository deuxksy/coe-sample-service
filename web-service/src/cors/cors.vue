<template>
  <div id="cors">
    <div class="toolbar">
      <label>url</label>
      <input v-model="url" placeholder="여기에 url을 입력해 주세요." />
      <button v-on:click="send">send</button>
    </div>
    <div class="body">
      {{ msg }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'cors',
  data () {
    return {
      msg: 'url 전송 결과가 여기에 표시됩니다.',
      url: ''
    }
  },
  methods: {
    send: function(event) {
      this.$http.get(this.url)
      .then((result) => {
        this.msg = result.data.name + " : " + result.data.email;
        console.log(result);
      })
      .catch((error) => {
        this.msg = error;
        console.log(error);
      })
    }
  }
}
</script>

<style>
.toolbar input{
  width: 500px;
}

.body {
  margin-top: 20px;
}
</style>
