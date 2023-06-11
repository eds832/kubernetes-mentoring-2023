{{/* Generate basic labels */}}
{{- define "mychart.labels" }}
  labels:
    version: {{ .Chart.Version }}
    currentDate: {{ now | htmlDate }}
{{- end }}