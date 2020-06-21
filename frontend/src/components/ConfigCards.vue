<template>
  <div>

<div  v-for="card in cards" style="display:inline-block;">
  <v-card
        class="mx-auto"
        max-width="344"
        outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline mb-4" v-text="card.id"></div>
          <v-list-item-title class="headline mb-1">Headline 5</v-list-item-title>
          <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-avatar
            tile
            size="80"
            color="grey"
        ></v-list-item-avatar>
      </v-list-item>

      <v-card-actions>
        <v-btn text>Button</v-btn>
        <v-btn text>Button</v-btn>
      </v-card-actions>
    </v-card>
</div>
  </div>
</template>

<script>
    export default {
        name: "ConfigCards",
        data () {
            return {
                cards : [
                    {id : 1},
                    {id : 2},
                    {id : 3},
                    {id : 4},
                ]
            }
        }
    }
</script>

<style scoped>

</style>