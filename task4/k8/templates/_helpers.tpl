{{/* Generate basic labels */}}
{{- define "mychart.labels" }}
  labels:
    version: 0.1.1
    currentDate: {{ now | htmlDate }}
{{- end }}