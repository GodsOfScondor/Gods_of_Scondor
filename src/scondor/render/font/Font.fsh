#version 330

in vec2 coords;

out vec4 out_color;

uniform sampler2D font;
uniform vec3 color;
uniform float transparency;

uniform float width;
uniform float edge;

uniform float border_width;
uniform float border_edge;

uniform vec2 offset;
uniform vec3 outline_color;

void main(void){
	
	float distance = 1.0 - texture(font, coords).a;
	float alpha = 1.0 - smoothstep(width, width + edge, distance);
	
	float distance2 = 1.0 - texture(font, coords+offset).a;
	float alpha2 = 1.0 - smoothstep(border_width, border_width + border_edge, distance2);
	
	float final_alpha = alpha + (1.0 - alpha) * alpha2;
	vec3 final_color = mix(outline_color, color, alpha / final_alpha);
	
	out_color = vec4(final_color ,min(transparency,final_alpha));
	
}