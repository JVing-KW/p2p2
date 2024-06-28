<template>
    <FullCalendar :options="calendarOptions" />
    <div>
        <button @click="test"></button>
    </div>
</template>

<script setup>
    import { defineProps } from 'vue'
    import FullCalendar from '@fullcalendar/vue3'
    import dayGridPlugin from '@fullcalendar/daygrid'
    // import interactionPlugin from '@fullcalendar/interaction'

    const props = defineProps({
        events: {
            type : Array
        }
    });

    const calendarOptions = {
        plugins: [dayGridPlugin],
        initialView: 'dayGridMonth',
        // currentEvents: props.events,
        eventContent : props.events
    }

    // Watch for changes in props.events and update calendarOptions
    // watch(() => props.events, (newEvents) => {
    //         calendarOptions.value = {
    //         ...calendarOptions.value,
    //         events: newEvents
    //     };
    // });

    function test(){
        console.log(props.events);
    }

</script>