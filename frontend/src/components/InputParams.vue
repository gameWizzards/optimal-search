<template>
  <v-container fluid>
    <v-row>

      <v-col
          cols="12"
          sm="2"
          class="py-2"
      >
        <p>Node Numbers</p>

        <v-text-field :rules="rules"></v-text-field>

      </v-col>

      <v-col
          cols="12"
          sm="2"
          class="py-2"
      >
        <p>Node Order</p>

        <v-text-field :rules="rules"></v-text-field>

      </v-col>

    </v-row>
    <v-row>
      <v-col
          cols="12"
          sm="2"
          class="py-2"
      >
        <p>Text Options</p>
        <v-combobox
            :items="items"
            label="Combobox"
            single
            outlined
            dense
        ></v-combobox>
      </v-col>
    </v-row>
    <v-row>
      <v-col
          cols="12"
          sm="2"
          class="py-2"
      >
        <v-btn
            color="primary"
            dark
            bottom
        >
          Search
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
    export default {
        data() {
            return {
                rules: [
                    value => !!value || 'Required.'
                    //value => (value || '').length <= 20 || 'Max 20 characters',
                    //value => {
                    //for email:
                    //const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                    //return pattern.test(value) || 'Invalid e-mail.'
                    //},
                ],
                items: [
                    'Only selected',
                    'Upper optimal',
                    'Down optimal',
                    'All configurations',
                ],
            }
        },
    }
</script>

<style scoped>

</style>