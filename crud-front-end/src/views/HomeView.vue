<script>
import axios, { Axios } from "axios";

const BASE_API_URL = "http://localhost:8080/api/v1/";

export default {
  data() {
    return {};
  },
  methods: {
    getPizza() {
      axios.get(BASE_API_URL + "/pizza").then();
    },
  },
};
</script>

<template>
  <main>helloworld</main>
</template>
