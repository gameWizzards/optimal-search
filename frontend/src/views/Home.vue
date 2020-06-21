<template>
  <div>
  <input-params />
  <config-cards />
  </div>
</template>

<script>
  import InputParams from "../components/InputParams";
  import ConfigCards from "../components/ConfigCards";

  export default {
    name: 'Home',

    components: {
      InputParams, ConfigCards
    },
  }
</script>
