<template>
    <FullCalendar :options="calendarOptions"/>
</template>

<script setup>
    import FullCalendar from '@fullcalendar/vue3'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import interactionPlugin from '@fullcalendar/interaction'

    const calendarOptions = {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        events: [
            {title : '면접', date: '2024-06-29'},
            {title : '면접', date: '2024-06-30'},
            {title : '면접', date: '2024-06-27'},
            {title : '면접', date: '2024-06-23'},
            {title : '서류마감', date: '2024-06-23'},
            {title : '면접', date: '2024-06-01'},
            {title : '생일', date: '2024-06-19'},
            {title : '면접', date: '2024-06-02'},
            {title : '웅냥냥', date: '2024-06-01'},
        ]
    }
</script>